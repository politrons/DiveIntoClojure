(ns clojure-server.Server
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defn persist-user []
  (str "{\"User-name\": \"Politrons\"}") )

(defn get-user [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "politrons"})

(defn create-user [req]
  {:status  200
   :headers {"Content-Type" "text/json"}
   :body    (persist-user)})

(defn general-handler [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Nothing is impossible!, Just do it!!!"})

(defroutes app-routes
           (GET "/user" [] get-user)
           (POST "/create" [] create-user)
           (ANY "/anything" [] general-handler)
           (route/not-found "What the hell are you trying to do?"))

;;  Main application
;; ------------------
(defn -main
  [& args]
  (let [port 1981]
    (server/run-server #'app-routes {:port port})
    (println (str "Running server at http:/127.0.0.1:" port "/"))))

