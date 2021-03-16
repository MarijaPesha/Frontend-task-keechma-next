(ns footer
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.helix.classified :refer [defclassified]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.helix.lib :refer [defnc]]))

(defclassified FooterOverlay :div "bg-gray-200 text-center text-xs p-3 absolute bottom-0 w-full")

(defnc Footer )