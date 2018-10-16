ring-middleware-case-format
===========================


A Ring's middleware that converts a request into X case and a response into a Y case

[![CircleCI](https://circleci.com/gh/druids/ring-middleware-case-format.svg?style=svg)](https://circleci.com/gh/druids/ring-middleware-case-format)
[![Dependencies Status](https://jarkeeper.com/druids/ring-middleware-case-format/status.png)](https://jarkeeper.com/druids/ring-middleware-case-format)
[![License](https://img.shields.io/badge/MIT-Clause-blue.svg)](https://opensource.org/licenses/MIT)


Leiningen/Boot
--------------

```clojure
[ring-middleware-case-format "0.1.0"]
```

Documentation
-------------

`wrap->kebab->snake` use as a normal middleware e.g.:

```clojure
(require '[ring.middleware.case-format :refer [wrap->kebab->snake]])
(wrap->kebab->snake
  (fn [request]
    {:body {:snake-case true}}))
```

`wrap->kebab->snake` converts all following keys from `request` into kebab-case:
  - `:params`
  - `:body-params`
  - `:form-params`
  - `:query-params`

and is converts a response's `:body` into snake\_case.

The keys will stay in their type (e.g. if a key is in keyword, it will be converted in a proper case and back into
 a keyword).

The namespace `ring.middleware.case-format` also exposed public functions `->camel`, `->kebab` and `->snake`.

Contribution
------------

### Conventions

* Please follow coding style defined by [`.editorconfig`](http://editorconfig.org)
 and [The Clojure Style Guide](https://github.com/bbatsov/clojure-style-guide)
* Write [good commit messages](https://chris.beams.io/posts/git-commit/)
 and provide an issue ID in a commit message prefixed by `#`
