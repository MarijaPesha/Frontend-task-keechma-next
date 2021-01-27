(ns app.controllers.article
  (:require
    [keechma.next.controller :as ctrl]
    [keechma.next.controllers.pipelines :as pipelines]
    [keechma.next.controllers.entitydb :as edb]
    [keechma.next.controllers.dataloader :as dl]
    [keechma.next.controllers.router :as router]
    [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
    [app.api :as api]))

(derive :article ::pipelines/controller)

;; TASK DRY
(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                (api/get-article (get-in @deps-state* [:router :id]))
                                (pp/swap! state* assoc :data (get-in value [:response :content])))

   :keechma.on/deps-change (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                (api/get-article (get-in @deps-state* [:router :id]))
                                (pp/swap! state* assoc :data (get-in value [:response :content])))
   })

(defmethod ctrl/prep :article [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :article [_ state {:keys [entitydb]}]
  (select-keys state [:data])
  )
