(ns app.controllers.search
  (:require
   [keechma.next.controller :as ctrl]
   [keechma.next.controllers.pipelines :as pipelines]
   [keechma.next.controllers.form :as form]
   [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
   [app.validators :as v]
   [promesa.core :as p]
   [app.api :as api]
   [keechma.next.controllers.entitydb  :as edb]
   [keechma.next.toolbox.logging :as l]))

(derive :search ::pipelines/controller)

(def pipelines
  {:keechma.form/submit-data (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                            ;;(l/pp "sub value" value)
                            (api/search (:search-value value))
                            ;; (l/pp "After" value)
                            ;; (l/pp "sub value" value)            
                            (edb/insert-collection! ctrl :entitydb :article :article/search-list (get-in value [:response :results])))})

(defmethod ctrl/prep :search [ctrl]
  (pipelines/register ctrl (form/wrap pipelines (v/to-validator {:search-value [:not-empty]}))))

(defmethod ctrl/derive-state :search [_ state {:keys [entitydb]}]
  (edb/get-collection entitydb :article/search-list))