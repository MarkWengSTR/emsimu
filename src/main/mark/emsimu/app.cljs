(ns mark.emsimu.app
  (:require [reagent.core :as r :refer [atom]]
            [ajax.core :refer [GET]]))

(defonce num-state (atom {:count-num 0}))

(def api-endpoint "http://localhost:5000/count")

(defn num-handler [response] 
        ;; notes:
        ;;  (assoc-in obj keyseq value) associates value with a
        ;;                              series of keys in a nested map
        ;;  (swap! atom assoc-in keyseq value)  does the same operation
        ;;                                      on a map inside an atom
        (let [{:strs [count_num]} response]
         (swap! num-state assoc-in [:count-num] count_num)))
          

(defn fetch-count! []
  (GET api-endpoint {:handler num-handler
                    ; :handler #(reset! number %)
                     :error-handler (fn [{:keys [status status-text]}]
                                      (js/console.log status status-text))}))

(defn hello-world []
  (fn []
    [:div.container
     [:h1 "API test button"]
     [:h2 (str "result value:" (:count-num @num-state))]
     [:input {:type "button" 
              :value "Click to call"
              :on-click #(fetch-count!)}]]))

(defn start []
  (r/render-component [hello-world] (js/document.getElementById "app")))

(defn stop []
  (js/console.log "stop"))

(start)
