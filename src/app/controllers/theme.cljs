(ns app.controllers.theme
  (:require
    [keechma.next.controller :as ctrl]
    [keechma.next.controllers.pipelines :as pipelines]
    [keechma.next.controllers.entitydb :as edb]
    [keechma.next.controllers.dataloader :as dl]
    [keechma.next.controllers.router :as router]
    [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
    [oops.core :refer [ocall ocall+]]
    [hodgepodge.core :refer [local-storage get-item set-item remove-item]]
    ))

(derive :theme ::pipelines/controller)

(defn set-theme [dark-theme?]
  (->
    (ocall js/document :getElementsByTagName "html")
    (first)
    (ocall+ (if dark-theme? :classList.add :classList.remove) "dark")))

(def pipelines
  {:keechma.on/start (pipeline! [value {:keys [state*] :as ctrl}]
                                (boolean (get-item local-storage "dark-theme"))
                                (pp/swap! state* assoc :dark-theme value)
                                (set-theme value))

   :toggle           (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                (set-item local-storage "dark-theme" (not (:dark-theme @state*)))
                                (set-theme (not (:dark-theme @state*)))
                                (pp/swap! state* assoc :dark-theme (not (:dark-theme @state*)))
                                )
   })

(defmethod ctrl/prep :theme [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :theme [_ state {:keys [entitydb]}]
  (select-keys state [:dark-theme])
  )
