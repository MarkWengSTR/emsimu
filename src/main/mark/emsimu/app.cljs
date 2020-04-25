(ns mark.emsimu.app
  (:require [reagent.core :as r]))

(defn intro []
  [:h1 "hahahs"])

(defn start []
  (r/render-component [intro] (js/document.getElementById "app")))

(defn stop []
  (js/console.log "stop"))

(start)
