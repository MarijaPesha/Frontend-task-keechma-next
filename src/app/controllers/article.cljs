(ns app.controllers.article
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [keechma.next.controllers.router :as router]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [app.api :as api]
            [keechma.next.toolbox.logging :as l]))

(derive :article ::pipelines/controller)

(def get-pipeline
  (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
             (api/get-article (get-in @deps-state* [:router :id]))
              ;; (l/pp value)
             (let [id (get-in value [:response :content :apiUrl])
                   content (get-in value [:response :content])
                   author (get-in value [:response :content :tags  0])]
               (l/pp "article controller" author)
               (edb/insert-named! ctrl :entitydb :article :article/current {:id id
                                                                            :content content
                                                                            :author author}))))

(def pipelines
  {:keechma.on/start        (pipeline! [value {:keys [deps-state*]}]
                                       (let [{:keys [entitydb router]} @deps-state*
                                             author-id (:id router)
                                             author (edb/get-entity entitydb :author author-id)]
                                         (if author
                                           (pipeline! [value ctrl]
                                                      (edb/insert-named! ctrl :entitydb :author :author/current author)
                                                      (pp/detached get-pipeline))
                                           get-pipeline)))})

(defmethod ctrl/prep :article [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state
  :article [_ state {:keys [entitydb]}]
  (edb/get-named entitydb :article/current [(edb/include :author)]))
