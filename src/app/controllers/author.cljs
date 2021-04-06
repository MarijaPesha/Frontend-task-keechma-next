(ns app.controllers.author
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.next.controllers.entitydb :as edb]
            [keechma.next.controllers.dataloader :as dl]
            [keechma.next.controllers.router :as router]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [app.api :as api]
            [keechma.next.toolbox.logging :as l]))

(derive :author ::pipelines/controller)

(def get-author
  (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
             (api/get-article (get-in @deps-state* [:router :id]))
             (let [author-map (get-in value [:response :tag])
                   id (:webUrl author-map)
                   author (:webTitle author-map)]
                ;;  (l/pp "author kontroler" id)
               (edb/insert-named! ctrl :entitydb :author :author/current {:author-map author-map 
                                                                          :id id 
                                                                          :author author}))))

(def pipelines
  {:keechma.on/start        get-author})

(defmethod ctrl/prep :author [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state
  :author [_ state {:keys [entitydb]}]
  (edb/get-named entitydb :author/current))