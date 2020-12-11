(ns app.ui.components.theme-toggler
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$ <>]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]))

(defnc ThemeTogglerRenderer [props]
       (let [dark-theme? (:dark-theme (use-sub props :theme))]
         (d/div
           (d/div {:class "mt-3 inline-flex items-center cursor-pointer relative"
                   :onClick #(dispatch props :theme :toggle)}
                  (d/span {:class "block w-10 h-6 bg-gray-400 rounded-full shadow-inner"})
                  (d/span {:class (str "absolute block w-4 h-4 mt-1 ml-1 rounded-full shadow inset-y-0 left-0 transition-all transition- duration-200 focus-within:shadow-outline transform translate-x-0 " (if dark-theme? " translate-x-full bg-purple-500" "bg-white ") )}))
           ))
       )

(def ThemeToggler (with-keechma ThemeTogglerRenderer))