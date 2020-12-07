(ns app.ui.components.header
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified HeadlineContainer :div
               "px-4 py-2 absolute mb-16")

(defclassified HeadlineContainerSmall :div
               "absolute h-full flex flex-col justify-around md:justify-end p-1 px-3 md:p-4 md:pb-6")

(defclassified HeadlineOverlay :h2
               "text-gray-900 font-bold inline max-w-1/2 uppercase")

(defclassified Tag :div
               "text-xs md:text-base my-1")

(defnc Headline [{:keys [link title] :as props}]
       ($ HeadlineOverlay
          (d/a {:href      link
                :className "bg-lime-400 px-1 py-1"
                :style     {:-webkit-box-decoration-break "clone"
                            :box-decoration-break         "clone"}}
               title)))

(defnc BigCard [{:keys [image date tag link title] :as props}]
       (d/div {:className "col-span-6 row-span-2 relative flex items-end justify-end text-md md:text-3xl background-gray-900"}
              (d/img {:className "object-cover w-full h-full opacity-50"
                      :src       image})
              ($ HeadlineContainer
                 ($ Tag tag)
                 ($ Headline {:link  link
                              :title title})
                 (d/div (str date)))
              )
       )

(defnc SmallCard [{:keys [image date tag link title] :as props}]
       (d/div {:className "col-span-3 row-span-1 relative flex h-full text-xs md:text-lg background-gray-900"}
              (d/img {:className "object-cover w-full h-full opacity-50"
                      :src       image})
              ($ HeadlineContainerSmall
                 ($ Tag tag)
                 ($ Headline {:link  link
                              :title title})
                 (d/div (str date)))
              ))

(defnc HeaderRenderer [{:keys [header-data] :as props}]
       (let [main-card (first header-data)]
         (d/div {:className "bg-gray-900 w-screen h-screen -mt-16 md:mt-0 relative md:h-auto text-gray-100 grid grid-cols-6 grid-rows-auto md:grid-cols-12 md:grid-rows-2"}
                ($ BigCard {:image (get-in main-card [:fields :thumbnail])
                            :date  (:webPublicationDate main-card)
                            :tag   (:sectionName main-card)
                            :link  (router/get-url props :router {:page "article" :id (:id main-card)})
                            :title (:webTitle main-card)})
                (map
                  (fn [item]
                    ($ SmallCard {:key   (:id item)
                                  :image (get-in item [:fields :thumbnail])
                                  :date  (:webPublicationDate item)
                                  :tag   (:sectionName item)
                                  :link  (router/get-url props :router {:page "article" :id (:id item)})
                                  :title (:webTitle item)})
                    )
                  (rest header-data)
                  )

                )))

(def Header (with-keechma HeaderRenderer))
