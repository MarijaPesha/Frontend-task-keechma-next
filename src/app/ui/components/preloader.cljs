(ns app.ui.components.preloader
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]])
  )


(defclassified PreloaderOverlay :div "dark:bg-gray-900 bg-gray-200 w-screen h-screen overflow-hidden flex items-center justify-center")

(defnc PreloaderRenderer [_]
  ($ PreloaderOverlay 
     (d/img {:width "128" :height "128" :alt "Loading" :src "images/loader.svg"}))
  )  
  
;; (d/div {:className "dark:bg-gray-900 bg-gray-200 w-screen h-screen overflow-hidden flex items-center justify-center"}
;; (d/img {:width "128" :height "128" :alt "Loading" :src "images/loader.svg"}))) 

(def Preloader (with-keechma PreloaderRenderer))