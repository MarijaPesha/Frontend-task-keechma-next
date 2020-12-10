## VBT TASK REPO

Hiring task for frontend developers of most levels

### Available tasks
This is a list of tasks that are scattered around the code, a more formalized approach should be taken in presenting those tasks and this list might better be omitted from the official testing.

List of possible tasks from simple to complex:

0. Register for the Guardian API and add it to the `settings.cljs` file (could be instructions)
1. Change Site Title
2. Change Site Favicon (could be some simple icon provided by us)
3. Change occurrences of Lime colour into Teal
4. Check the `get-education` API endpoint and fix it
5. Add a footer to the `Article.cljs` and `Home.cljs` pages
    1. Make it a reusable component
    2. Keep responsive design in mind
6. Move the Preloader component to a separate file
7. Add and style a simple 404 page
    1. Keep responsive styles in mind
8. Use the DRY principle and optimize `navbar.cljs` component links 
9. Use the DRY principle and optimize the `article.cljs` controller
10. Add parameters to the API calls
11. On the homepage, hovering on an article should show a popover preview of the article limited to 200 characters 

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

Runs the interactive live development enviroment.<br>
You can use it to design, test, and think about parts of your app in isolation.

Use [http://localhost:3000/workspaces.html](http://localhost:3000/workspaces.html) to inspect.

This environment uses [Workspaces](https://github.com/nubank/workspaces) and [React Testing Library](https://testing-library.com/docs/react-testing-library/intro).

### `yarn build`

Builds the app for production to the `public` folder.<br>
It correctly bundles all code and optimizes the build for the best performance.

Your app is ready to be deployed!

## Other useful scripts

### `null` and `yarn e2e`

You can use `null` to run tests a single time, and `yarn e2e` to run the end-to-end test app.
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

