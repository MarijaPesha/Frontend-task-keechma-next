(ns app.ui.pages.home
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$ <>]]
            [keechma.next.helix.core :refer [with-keechma use-sub use-meta-sub]]
            [keechma.next.controllers.pipelines :refer [get-promise]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.lib :refer [defnc]]
    ;;[keechma.next.helix.template :refer [defnt fill-slot configure]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.navbar :refer [Navbar]]
            [app.ui.components.header :refer [Header]]))

(defclassified HomepageWrapper :div "font-open-sans h-screen w-screen flex flex-col bg-gray-200 overflow-x-hidden overflow-y-scroll dark:bg-gray-900 dark:text-gray-100")

;; TASK componentize preloader
(defnc Preloader [props]
       (d/div {:className "dark:bg-gray-900 bg-gray-200 w-screen h-screen overflow-hidden flex items-center justify-center"}
              (d/img {:width  "128"
                      :height "128"
                      :alt    "Loading"
                      :src    "images/loader.svg"})))

(defnc HomeRenderer [props]
       (let [feed-data (:results (use-sub props :feed))
             feed-data-meta (use-meta-sub props :feed)
             loading-feed (get-promise feed-data-meta :feed-data)
             subpage (:subpage (use-sub props :router))]
         ($ HomepageWrapper
            ($ Navbar)
            (if loading-feed
              ($ Preloader)
              (<>
                ($ Header {:header-data (take 5 feed-data)})
                (d/div {:class "dark:bg-gray-800 bg-gray-100 flex flex-1 flex-col items-center justify-center px-6 md:px-12 w-full md:w-3/5 mx-auto shadow-xl py-12"}
                       (d/div {:className "text-lg md:text-2xl w-full text-left px-4 py-6 border-b border-gray-500"}
                              (case subpage
                                "tech" "Technology News"
                                "education" "Education News"
                                "sport" "Sport News"
                                nil "All News"))
                       (map
                         (fn [item]
                           (d/div {:key       (:id item)
                                   :className "flex justify-between items-center w-full border-b border-gray-500 py-6"
                                   :onClick   #(router/redirect! props :router {:page "article" :id (:id item)})}

                                  (d/div {:className "relative"}
                                         (d/img {:className "w-16 h-16 md:w-32 md:h-32 z-50 relative object-cover"
                                                 :src       (get-in item [:fields :thumbnail] "https://picsum.photos/200")})

                                         (d/div {:className "absolute w-16 h-16 md:w-32 md:h-32 bg-lime-500 top-0 left-0 z-10 transform translate-y-1 translate-x-1"})
                                         )
                                  (d/div {:className "cursor-pointer text-left w-3/4 ml-4"}
                                         (d/h3 {:className "inline text-base md:text-xl border-b-4  border-lime-500"} (:webTitle item))
                                         (d/p {:className "dark:text-gray-400 text-gray-700 text-sm mt-4"}
                                              (:sectionName item) " | " (:webPublicationDate item)))))
                         (nthrest feed-data 5))
                       ))))))

(def Home (with-keechma HomeRenderer))