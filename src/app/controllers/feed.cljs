(ns app.controllers.feed
  (:require
    [keechma.next.controller :as ctrl]
    [keechma.next.controllers.pipelines :as pipelines]
    [keechma.next.controllers.entitydb :as edb]
    [keechma.next.controllers.dataloader :as dl]
    [keechma.next.controllers.router :as router]
    [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
    [app.api :as api]))

(derive :feed ::pipelines/controller)

(def get-data-pipeline
  (-> (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                 (case (get-in @deps-state* [:router :subpage])
                   "tech" (api/get-tech nil)
                   "education" (api/get-education nil)
                   "sport" (api/get-sport nil)
                   nil (api/get-feed nil))
                 (pp/swap! state* assoc :results (get-in value [:response :results])))
      (pp/set-queue :feed-data))
  )

(def pipelines
  {:keechma.on/start       get-data-pipeline
   :keechma.on/deps-change get-data-pipeline

   :keechma.on/stop        (pipeline! [_ ctrl])})

(defmethod ctrl/prep :feed [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :feed [_ state {:keys [entitydb]}]
  (select-keys state [:results])
  )
