(ns app.ui.components.navbar
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma dispatch call use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [oops.core :refer [ocall oget]]))

(defnc ThemeToggler [props]
       (d/div
         (d/div {:class "mt-3 inline-flex items-center cursor-pointer relative"}
                (d/span {:class "block w-10 h-6 bg-gray-400 rounded-full shadow-inner"})
                (d/span {:class "absolute block w-4 h-4 mt-1 ml-1 dark:bg-teal bg-white rounded-full shadow inset-y-0 left-0 focus-within:shadow-outline transform translate-x-0 dark:translate-x-full"}))
         (d/button
           {:className "dark:bg-black bg-white dark:text-white text-black"
            :onClick   #(ocall (first (ocall js/document :getElementsByTagName "html")) :classList.toggle "dark")}
           "Toggle theme"
           ))
       )

(defnc NavbarRenderer [props]
       (d/div {:className "dark:bg-black bg-gray-900 w-full text-gray-100 h-16 py-0 fixed md:relative top-0 z-10 flex"}
              (d/div {:className "w-full md:w-1/2 mx-auto flex justify-around items-center font-open-sans text-lg"}
                     (d/a {:className "flex items-center justify-center flex-col lg:flex-row lg:justify-around border-b-4  dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300  pt-0 md:pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home"})}

                          (d/p "Very Big")
                          (d/p {:className "lg:ml-2 text-xl font-bold"}
                               "News"))
                     ;;TASK DRY
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "tech"})}
                          "Tech")
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "education"})}
                          "Education")
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "sport"})}
                          "Sport")
                     (d/button {:onClick #(ocall js/window :classList)})

                     ($ ThemeToggler)
                     )
              ))

(def Navbar (with-keechma NavbarRenderer))