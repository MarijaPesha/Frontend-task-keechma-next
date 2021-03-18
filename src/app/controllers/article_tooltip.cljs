(ns app.controllers.article-tooltip
  (:require [app.api :as api]
            [keechma.next.controller :as ctrl]
            [keechma.next.controllers.router    :as router]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.entitydb  :as edb]))


(derive :article-tooltip ::pipelines/controller)

(def pipelines
  {:load-article-tooltip (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                   (api/get-article (:article-id value))
                               
                                   (pp/swap! state* assoc :article-tooltip (subs (get-in value [:response :content :blocks :body 0 :bodyTextSummary]) 0 200)))
   }
  )

(defmethod ctrl/prep :article-tooltip [ctrl]
  (pipelines/register ctrl pipelines))

(defmethod ctrl/derive-state :article-tooltip [_ state {:keys [entitydb]}]
  (:article-tooltip state))