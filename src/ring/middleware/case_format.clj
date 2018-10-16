(ns ring.middleware.case-format
  (:require
    [clojure.walk :refer [postwalk]]
    [camel-snake-kebab.core :refer [->kebab-case ->snake_case ->camelCase]]))


(defmulti ^:private format-key
  (fn [k format-fn]
    (type k)))

(defmethod ^:private format-key java.lang.String
  [k format-fn]
  (format-fn k))

(defmethod ^:private format-key clojure.lang.Keyword
  [k format-fn]
  (-> k name format-fn keyword))

(defmethod ^:private format-key :default
  [k _]
  k)


(defn- format-hashmap-key
  "Formats a given key value pair by a given `format-fn` and returns as a vector."
  [format-fn [k v]]
  [(format-key k format-fn) v])


(defn- format-keys
  "Maps a format function onto a hash-map keys"
  [format-fn data]
  (let [f (partial format-hashmap-key format-fn)]
    (postwalk (fn [x]
                (if (map? x)
                  (into {} (map f x))
                  x))
              data)))


(def ->kebab (partial format-keys ->kebab-case))

(def ->snake (partial format-keys ->snake_case))

(def ->camel (partial format-keys ->camelCase))


(defn wrap->kebab->snake
  "A middleware that converts a request parameters into kebab-case and converts a response's body into snake_case."
  [handler]
  (fn [request]
    (let [response (handler (-> request
                                (update :params ->kebab)
                                (update :body-params ->kebab)
                                (update :form-params ->kebab)
                                (update :query-params ->kebab)))]
      (update response :body ->snake))))
