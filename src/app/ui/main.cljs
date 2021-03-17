(ns app.ui.main
  (:require [keechma.next.helix.core :refer [with-keechma use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.core :as hx :refer [$]]
            [helix.dom :as d]
            [app.ui.pages.home :refer [Home]]
            [app.ui.pages.article :refer [Article]]
            [app.ui.components.error :refer [Error404]]
            ;; [clojure.core.match :refer [match]]
            ))


;; TASK move navbar here DRY
;; TASK add footer
;; TASK 404 PAGE
(defnc MainRenderer [props]
  (let [{:keys [page]} (use-sub props :router)]
    (case page
      "home" ($ Home)
      "article" ($ Article)
      ($ Error404))))

(def Main (with-keechma MainRenderer))
