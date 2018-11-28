(ns ring.middleware.case-format-test
  (:require
    [clojure.test :refer [deftest is]]
    [ring.middleware.case-format :refer [wrap->kebab->snake ->camel ->kebab ->snake]]))


(deftest wrap->kebab->snake-test
  (let [handler (wrap->kebab->snake (fn [request]
                                      (is (= {:params {"snake-case" "1"}
                                              :body-params {:snake-case 2, nil nil, 3 3}
                                              :form-params {:snake-case 3}
                                              :query-params {"snake-case" "4"}}
                                             request))
                                      {:body {:kebab-case true}}))]
    (is (= {:body {:kebab_case true}}
           (handler {:params {"snake_case" "1"}
                     :body-params {:snake_case 2, nil nil, 3 3}
                     :form-params {:snake_case 3}
                     :query-params {"snake_case" "4"}})))))

(deftest formatters-test
  (is (= {:camelCase 1} (->camel {:camel-case 1})))
  (is (= {:kebab-case 1} (->kebab {:kebabCase 1})))
  (is (= {:snake_case 1} (->snake {:snake-case 1})))
  (is (= {:ring.middleware.case_format_test/snake_case 1} (->snake {::snake-case 1}))))
