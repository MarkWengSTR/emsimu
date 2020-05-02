(ns mark.emsimu.app
  (:require [reagent.core :as r :refer [atom]]
            [ajax.core :refer [GET]]))

(defonce app-state (atom {:count 0}))

(def api-endpoint "http://localhost:5000/count")

(defn handler [response] 
        ;; notes:
        ;;  (assoc-in obj keyseq value) associates value with a
        ;;                              series of keys in a nested map
        ;;  (swap! atom assoc-in keyseq value)  does the same operation
        ;;                                      on a map inside an atom
        (swap! app-state assoc-in [:count] (response "count")))

(defn add-load-func [f]
  (let [old-onload (.-onload js/window)]
    (set! (.-onload js/window)
          (if (nil? old-onload)
            f
            (fn [_] (old-onload) (f))))))

(defn api-call []
 (add-load-func (fn [] (GET api-endpoint {:handler handler}
                                    :response-format :json
                                     :keywords? true))))
  

(defn hello-world []
  [:div.container
   [:h1 "API test button"]
   [:h2 (str "result value:")]
   [:input {:type "button" 
            :value "Click to call"
            :on-click (:count @app-state)}]])

(defn start []
  (r/render-component [hello-world] (js/document.getElementById "app")))

(defn stop []
  (js/console.log "stop"))

(start)
