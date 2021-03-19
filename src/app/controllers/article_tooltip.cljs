(ns app.controllers.article-tooltip
  (:require [app.api :as api]
            [keechma.next.toolbox.logging :as l]
            [keechma.next.controller :as ctrl]
            [keechma.next.controllers.router    :as router]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.entitydb  :as edb]))


(derive :article-tooltip ::pipelines/controller)

#_(def pipelines
  {:load-article-tooltip (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                   (api/get-article (:article-id value))
                               
                                   (pp/swap! state* assoc :article-tooltip (subs (get-in value [:response :content :blocks :body 0 :bodyTextSummary]) 0 200)))
   }
  )


;; task 12. pospremanje data u EntityDB
(def pipelines
  {:load-article-tooltip (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                                    (api/get-article (:article-id value))
                                    (subs (get-in value [:response :content :blocks :body 0 :bodyTextSummary]) 0 200)
                                ;;  (l/pp "value in ctrl" value)
                                    (edb/insert-named! ctrl :entitydb :article-tooltip :article-tooltip/current {:id value
                                                                                                                 :tooltip value}))          
   }
  )


(defmethod ctrl/prep :article-tooltip [ctrl]
  (pipelines/register ctrl pipelines))

#_(defmethod ctrl/derive-state :article-tooltip [_ state {:keys [entitydb]}]
  (:article-tooltip state))

;; task 12. pospremanje data u EntityDB
(defmethod ctrl/derive-state :article-tooltip [_ state {:keys [entitydb]}]
  (:tooltip  (edb/get-named entitydb :article-tooltip/current)))