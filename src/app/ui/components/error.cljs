(ns app.ui.components.error
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]))


 (defclassified OopsWrapper :div "dark:bg-gray-800 text-white min-h-screen flex items-center")
 (defclassified OopsContainer :div "dark:bg-gray-800 container mx-auto p-4 flex flex-wrap  items-center")
 
 (defclassified OopsTextWrapper :div "dark:bg-gray-800 text-white min-h-screen flex items-center flex flex-col m-auto justify-center")
 (defclassified OopsNum :div "dark:bg-gray-800 text-6xl font-medium")
 (defclassified OopsTextHead :div "dark:bg-gray-800 text-xl md:text-3xl font-medium mb-4 text-center")
 (defclassified OopsTextPara :div "dark:bg-gray-800 text-lg mb-8 text-center")
 
 (defnc ErrorRenderer [props]
 ($ OopsWrapper 
  ($ OopsContainer 
   (d/div {:className "w-full md:w-5/12 p-4 mx-auto"}
    (d/img {:src "https://themichailov.com/img/not-found.svg" :alt "Not Found"}))
       ($ OopsTextWrapper 
    ($ OopsNum "404")
    ($ OopsTextHead "Oops. This page has gone missing.")
    ($ OopsTextPara "You may have mistyped the address or the page may have moved.")
         (d/button {:className "border border-white rounded p-4"
                    :onClick   #(router/redirect! props :router {:page "home"})} 
                   "Go Home")
    )
   )
  )
 )

(def Error404 (with-keechma ErrorRenderer))