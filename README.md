# ClojureScript React app architecture

This is an example of client-side clojurescript React-based app architecture.
Inspired by [circleci/frontend](https://github.com/circleci/frontend/)
project (explained in
[this talk](https://www.youtube.com/watch?v=LNtQPSUi1iQ&list=PLZdCLR02grLrKAOj8FJ1GGmNM5l7Okz0a)).

## Developing

```shell
$ git clone https://github.com/mmavko/cs-react-arch
$ cd cs-react-arch
$ lein figwheel dev
$ open http://localhost:3449/
```

Figwheel will also give you a REPL linked to the browser.

Project gets configured with "gitignored" `resources/public/config.js` file. Please create your own
(use `config.example.js` as an example).

## Making production build

```shell
$ lein clean
$ lein cljsbuild once min
```

It will make an independent build in file `resources/public/js/compiled/cs-react-arch.js`.
You can run a webserver rooted at `resources/public` and try a build in work.

## Integration

Please see `resources/public/index.html` to get an example of configuring and running
the project.
