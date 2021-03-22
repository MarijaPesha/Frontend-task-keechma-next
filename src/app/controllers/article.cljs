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

;; TASK DRY
 
 #_(def get-pipeline
   (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
              (api/get-article (get-in @deps-state* [:router :id]))
              (pp/swap! state* assoc :data (get-in value [:response :content]))))


  (def get-pipeline 
    (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
               (api/get-article (get-in @deps-state* [:router :id]))
               ;; (l/pp "value in ctrl" value)
               (let [id (get-in value [:response :content :apiUrl])
                    content (get-in value [:response :content ])]
               ;; (l/pp "contetnt in ctrl" content)
                 (edb/insert-named! ctrl :entitydb :article :article/current {:id id
                                                                                   :content content })
                 )
               ))

(def pipelines
  {:keechma.on/start        get-pipeline
    ;;                      (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
    ;;                      (api/get-article (get-in @deps-state* [:router :id]))
    ;;                      (pp/swap! state* assoc :data (get-in value [:response :content])))
    ;; keechma.on/deps-change  get-pipeline
    ;;                      (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
    ;;                      (api/get-article (get-in @deps-state* [:router :id]))
    ;;                      (pp/swap! state* assoc :data (get-in value [:response :content])))})
                              })

(defmethod ctrl/prep :article [ctrl]
  (pipelines/register ctrl pipelines))

#_(defmethod ctrl/derive-state :article [_ state {:keys [entitydb]}]
    (select-keys state [:data]))

(defmethod ctrl/derive-state 
  :article [_ state {:keys [entitydb]}]
    (edb/get-named entitydb :article/current))
