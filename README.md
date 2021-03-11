## VBT TASK REPO

Hiring task for frontend developers of most levels


### Setup

Follow the instructions and register for `developer` [guardian API key](https://open-platform.theguardian.com/access/).
Once obtained, replace `<YOUR GUARDIAN API KEY HERE>` in `app.settings/api-key` file.
Refer to the [API docs](https://open-platform.theguardian.com/documentation) when needed.

### Available tasks
This is a list of tasks that are scattered around the code, a more formalized approach should be taken in presenting those
 tasks and this list might better be omitted from the official testing.

List of possible tasks from simple to advanced:

1. Change the Site Title
2. Change the Site Favicon (could be some simple icon provided by us)
3. Change occurrences of `Lime` colour into `Teal` (check [Tailwind](https://tailwindcss.com/docs/configuration) Config for details)
4. Check the `get-education` API endpoint and fix it
5. Add a footer to the `Article.cljs` and `Home.cljs` pages
    - Make it a reusable component
    - Keep responsive design in mind
6. Extract `Preloader` component to a separate file
7. Add and style a simple 404 page
    - Keep responsive styles in mind
    - Update routing to accommodate new route
    - Extra: try to use `match` instead of `case` for better pattern matching [core.match](https://github.com/clojure/core.match)
8. Use the DRY principle and optimize `navbar.cljs` component links 
9. Use the DRY principle and optimize the `article.cljs` controller
10. Add parameters to the API calls
11. On the homepage, hovering on an article should show a popover preview of the article limited to 200 characters
    - Tips:
    - In a controller:
        + make an appropriate API request to get the data you need
        + save the data in the controller state
        + use the `derive-state` method to prepare the data for the UI
    - In the UI:
        + subscribe to the controller in question
        + create the tooltip ([suggestion](https://tailwindcomponents.com/component/tooltip))
        + add the data to the tooltip 
12. Instead of using state in controllers to store the data, save it in the EntityDB instead. 
    Try to look for other controllers where you can do the same. [EntityDB docs](https://github.com/keechma/keechma-entitydb)
    - to insert things into EntityDB, you have to depend on it first (it's a controller also)
    - there are two main functions you can use to insert data:
        - `(edb/insert-named! ctrl :entitydb :_type_ :_name_ value))`, where value is of format `{...}`
        - `(edb/insert-collection! ctrl :entitydb :_type_ :_name_ value))`, where value is of format `[{...}...{...}]`
        - first function is used to insert single element, while the second function is used to insert a collection of
          elements
        - `_type_` is a type of element(s) to be inserted
        _ `_name_` is a namespaced key under which the element(s) will be inserted; the convention we use is following:
          `:type/association` - where type is again the type of the element(s), and the association is a suggestive name
          that functions as a both data descriptor and grammatical number, ie:
          - `user/current`
          - `user/list`
          - `article/favorite-list`
        - _note that the type is *always* in singular form, and the association functions as singular/plural indicator_        
13. Optimize controller params for their intended use
    -`:keechma.controller/params` found on each controller in the `app.app` dictates the controller lifecycle
    - many of them are set to `true` meaning controller will immediately
    - ideally you want to set up controllers in such fashion that they're alive for the purpose of their intended use
    - you can specify other controllers they depend on in `:keechma.controller/deps` key, and based on them come up with the causality you need    
14. Implement `Search` page with knowledge from before (note: this includes form controller which hasn't been covered, ask for instructions for now)
    - [API docs](https://open-platform.theguardian.com/documentation/search)
    + create a new page called `Search`
    + create two parts of the page: one which contains the form used for search, and the other which will list the results
    + create a new form controller file which will handle the form and declare it in `app` map
    + connect the UI with the form controller
    + add new API search fn which will receive the input from the form and use it to fetch the data from the API
    + connect it all together, do the request
    + store the data, prepare for the UI and show it in the list
    - extra: there are different scenarios that will impact how you model the feature, be sure to play with them and ask
    the instructor to help you out (ie, search term as a part of the URL (_think about the consequences_))

FORM EXAMPLE
```
(d/form
    {:onSubmit (fn [e]
                 (.preventDefault e)
                 (dispatch props :_ctrl-name_ :keechma.form/submit))}
    (wrapped-input {:keechma.form/controller :_ctrl-name_,
                    :input/type :text,
                    :input/attr :name,
                    :placeholder "John"})
    (d/button "SUBMIT NAME"))
```

FORM CONTROLLER EXAMPLE
```
(ns app.controllers._ctrl-name_
  (:require
   [keechma.next.controller :as ctrl]
   [keechma.next.controllers.pipelines :as pipelines]
   [keechma.next.controllers.form :as form]
   [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
   [app.validators :as v]
   [promesa.core :as p]
   [app.api :as api]))

(derive :_ctrl-name_ ::pipelines/controller)

(def pipelines
  {:keechma.form/submit-data (pipeline! [value {:keys [deps-state* state*] :as ctrl}]
                              ;; do something with submitted data)})

(defmethod ctrl/prep :_ctrl-name_ [ctrl]
  (pipelines/register ctrl (form/wrap pipelines (v/to-validator {}))))

(defmethod ctrl/derive-state :_ctrl-name_ [_ state deps-state}]
  ;;prepare some data)
```



## Code Related Information

### Available Scripts

In the project directory, you can run:

### `yarn start`

Runs the app in development mode.<br>
Open [http://localhost:3000](http://localhost:3000) to view it in the browser.
The page will reload if you make edits.

Builds use [Shadow CLJS](https://github.com/thheller/shadow-cljs) for maximum compatibility with NPM libraries. You'll need a [Java SDK](https://adoptopenjdk.net/) (Version 8+, Hotspot) to use it. <br>
You can [import npm libraries](https://shadow-cljs.github.io/docs/UsersGuide.html#js-deps) using Shadow CLJS. See the [user manual](https://shadow-cljs.github.io/docs/UsersGuide.html) for more information.

### `yarn cards`

Runs the interactive live development environment.<br>
You can use it to design, test, and think about parts of your app in isolation.

Use [http://localhost:3000/workspaces.html](http://localhost:3000/workspaces.html) to inspect.

This environment uses [Workspaces](https://github.com/nubank/workspaces) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).

### `yarn build`

Builds the app for production to the `public` folder.<br>
It correctly bundles all code and optimizes the build for the best performance.

Your app is ready to be deployed!

## Other useful scripts

### `yarn test` and `yarn e2e`

You can use `test:once` instead to run the tests a single time,  and `yarn e2e` to run the end-to-end test app.
`yarn test` launches tests in interactive watch mode.<br>

See the ClojureScript [testing page](https://clojurescript.org/tools/testing) for more information. E2E tests use [Taiko](https://github.com/getgauge/taiko) to interact with a headless browser.

### `yarn lint` and `yarn format`

`yarn lint` checks the code for known bad code patterns using [clj-kondo](https://github.com/borkdude/clj-kondo).

`yarn format` will format your code in a consistent manner using [zprint-clj](https://github.com/clj-commons/zprint-clj).

### `yarn report`

Make a report of what files contribute to your app size.<br>
Consider [code-splitting](https://code.thheller.com/blog/shadow-cljs/2019/03/03/code-splitting-clojurescript.html) or using smaller libraries to make your app load faster.

### `yarn server`

Starts a Shadow CLJS background server.<br>
This will speed up starting time for other commands that use Shadow CLJS.

## Useful resources

Clojurians Slack http://clojurians.net/.

CLJS FAQ (for JavaScript developers) https://clojurescript.org/guides/faq-js.

Official CLJS API https://cljs.github.io/api/.

Quick reference https://cljs.info/cheatsheet/.

Offline searchable docs https://devdocs.io/.

VSCode plugin https://github.com/BetterThanTomorrow/calva.

This project was bootstrapped with [Create CLJS App](https://github.com/filipesilva/create-cljs-app).

