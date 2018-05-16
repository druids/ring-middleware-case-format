(ns ring.middleware.case-format-test
  (:require
    [clojure.test :refer [deftest is]]
    [ring.middleware.case-format :refer [wrap->kebab->snake]]))


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

