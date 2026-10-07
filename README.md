<div align="center">

# Java Patterns Lab

### A worked catalog of classic Gang-of-Four design patterns — problem, solution, UML, runnable code and tests for each

[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)](https://openjdk.org/)
[![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![License](https://img.shields.io/badge/license-MIT-lightgrey)](#license)

</div>

---

## 📖 About the project

Every pattern in this repo is implemented against the **same running example** — a small e-commerce checkout domain (orders, payments, invoices, support tickets, reports, shipping, catalog browsing) — so the catalog reads as one coherent story instead of a pile of disconnected toy snippets. Each pattern has:

- a **doc page** under [`docs/patterns/`](docs/patterns) with the problem it solves, the solution, and a Mermaid UML class diagram (renders directly on GitHub);
- **runnable source** under `src/main/java/com/javapatternslab/<pattern>/`, including a `*Demo` class with a `main()` you can run directly;
- a **JUnit 5 test** under `src/test/java/com/javapatternslab/<pattern>/` exercising the pattern's actual behavior, not just that it compiles.

This is a learning/reference project — a place to point to a concrete, working, tested example of each pattern rather than re-explain it from scratch every time it comes up.

## 📚 Pattern catalog

| # | Pattern | Category | Problem in one line | Docs | Source |
|---|---|---|---|---|---|
| 1 | Strategy | Behavioral | Swap a checkout's payment algorithm at runtime without `if/else` chains | [docs](docs/patterns/strategy.md) | [`strategy/`](src/main/java/com/javapatternslab/strategy) |
| 2 | Factory Method | Creational | Let subclasses decide which concrete `Notification` to construct | [docs](docs/patterns/factory.md) | [`factory/`](src/main/java/com/javapatternslab/factory) |
| 3 | Observer | Behavioral | Notify inventory, email and analytics when an order's status changes, without coupling them to `Order` | [docs](docs/patterns/observer.md) | [`observer/`](src/main/java/com/javapatternslab/observer) |
| 4 | Builder | Creational | Construct a multi-field `Invoice` step by step, with optional parts, without a telescoping constructor | [docs](docs/patterns/builder.md) | [`builder/`](src/main/java/com/javapatternslab/builder) |
| 5 | Adapter | Structural | Make a legacy, string-based payment gateway speak the app's modern `PaymentGateway` interface | [docs](docs/patterns/adapter.md) | [`adapter/`](src/main/java/com/javapatternslab/adapter) |
| 6 | Decorator | Structural | Add gift-wrap, express shipping and insurance pricing to a `Product` without a subclass per combination | [docs](docs/patterns/decorator.md) | [`decorator/`](src/main/java/com/javapatternslab/decorator) |
| 7 | Chain of Responsibility | Behavioral | Route a support ticket through L1 → L2 → Manager until someone can handle its severity | [docs](docs/patterns/chain-of-responsibility.md) | [`chainofresponsibility/`](src/main/java/com/javapatternslab/chainofresponsibility) |
| 8 | Template Method | Behavioral | Share the fetch → format → export skeleton across PDF and CSV report generators | [docs](docs/patterns/template-method.md) | [`templatemethod/`](src/main/java/com/javapatternslab/templatemethod) |
| 9 | Command | Behavioral | Turn "place order" / "cancel order" into objects so they can be queued, logged and undone | [docs](docs/patterns/command.md) | [`command/`](src/main/java/com/javapatternslab/command) |
| 10 | Singleton | Creational | Guarantee exactly one shared, lazily-created `CheckoutConfig` across the whole app | [docs](docs/patterns/singleton.md) | [`singleton/`](src/main/java/com/javapatternslab/singleton) |
| 11 | Abstract Factory | Creational | Produce a matched label + customs-form pair per shipping mode, so the two can never mismatch | [docs](docs/patterns/abstract-factory.md) | [`abstractfactory/`](src/main/java/com/javapatternslab/abstractfactory) |
| 12 | Facade | Structural | Hide inventory + payment + shipping + notification coordination behind one `placeOrder()` call | [docs](docs/patterns/facade.md) | [`facade/`](src/main/java/com/javapatternslab/facade) |
| 13 | Proxy | Structural | Cache an expensive product lookup transparently, behind the same `ProductCatalog` interface | [docs](docs/patterns/proxy.md) | [`proxy/`](src/main/java/com/javapatternslab/proxy) |
| 14 | Composite | Structural | Price a cart item and an arbitrarily nested bundle of items through the same interface | [docs](docs/patterns/composite.md) | [`composite/`](src/main/java/com/javapatternslab/composite) |
| 15 | State | Behavioral | Make invalid order transitions (e.g. shipping a `CREATED` order) impossible without `if` chains | [docs](docs/patterns/state.md) | [`state/`](src/main/java/com/javapatternslab/state) |
| 16 | Iterator | Behavioral | Traverse order history two different ways (all orders, paid-only) without exposing its internal list | [docs](docs/patterns/iterator.md) | [`iterator/`](src/main/java/com/javapatternslab/iterator) |
| 17 | Prototype | Creational | Create each recurring order by copying a registered template, without the copy ever changing the template | [docs](docs/patterns/prototype.md) | [`prototype/`](src/main/java/com/javapatternslab/prototype) |
| 18 | Bridge | Structural | Combine any report type (sales, inventory) with any output format (text, HTML) without a class per pair | [docs](docs/patterns/bridge.md) | [`bridge/`](src/main/java/com/javapatternslab/bridge) |
| 19 | Flyweight | Structural | Let thousands of order lines share one immutable object per product instead of each holding a copy | [docs](docs/patterns/flyweight.md) | [`flyweight/`](src/main/java/com/javapatternslab/flyweight) |
| 20 | Visitor | Behavioral | Add tax and shipping calculations over a cart tree without adding a method to every item class | [docs](docs/patterns/visitor.md) | [`visitor/`](src/main/java/com/javapatternslab/visitor) |
| 21 | Mediator | Behavioral | Keep cart, coupon, shipping and the place-order button in sync without them referencing each other | [docs](docs/patterns/mediator.md) | [`mediator/`](src/main/java/com/javapatternslab/mediator) |
| 22 | Memento | Behavioral | Checkpoint an order draft and undo back to it without exposing the draft's internal state | [docs](docs/patterns/memento.md) | [`memento/`](src/main/java/com/javapatternslab/memento) |
| 23 | Interpreter | Behavioral | Keep promotion rules as text (`total >= 300 AND items >= 2`) and evaluate them against any order | [docs](docs/patterns/interpreter.md) | [`interpreter/`](src/main/java/com/javapatternslab/interpreter) |

That is all 23 patterns from the original Gang-of-Four book: 5 creational, 7 structural and 11 behavioral.

## 🚀 Running it

```bash
mvn -B clean test                                    # compile + run all pattern tests

# run any pattern's demo directly, e.g. Strategy:
mvn -q compile exec:java -Dexec.mainClass="com.javapatternslab.strategy.StrategyDemo"
```

Each `*Demo` class is a plain `public static void main` — no `exec-maven-plugin` is wired into `pom.xml` by default (kept dependency-light), so if `exec:java` isn't available, just run the class from your IDE, or:

```bash
mvn -q compile
javac -cp target/classes -d /tmp/out src/main/java/com/javapatternslab/strategy/*.java
java -cp target/classes com.javapatternslab.strategy.StrategyDemo
```

## 🗂️ Project layout

```
java-patterns-lab/
├── docs/patterns/          One .md per pattern: problem, solution, Mermaid UML
├── src/main/java/com/javapatternslab/
│   ├── strategy/
│   ├── factory/
│   ├── observer/
│   ├── builder/
│   ├── adapter/
│   ├── decorator/
│   ├── chainofresponsibility/
│   ├── templatemethod/
│   ├── command/
│   ├── singleton/
│   ├── abstractfactory/
│   ├── facade/
│   ├── proxy/
│   ├── composite/
│   ├── state/
│   ├── iterator/
│   ├── prototype/
│   ├── bridge/
│   ├── flyweight/
│   ├── visitor/
│   ├── mediator/
│   ├── memento/
│   └── interpreter/
└── src/test/java/com/javapatternslab/   (mirrors src/main, one test class per pattern)
```

## 🧭 Where this fits

This is the first of a small portfolio of independent projects, each built to demonstrate a different competency:

- **java-patterns-lab** (this repo) — OO design fundamentals: the classic GoF patterns, worked and tested.
- [Social Supply Management](https://github.com/duanjesus/social-supply-management-api) — layered CRUD architecture.
- [CashPilot](https://github.com/duanjesus/cashpilot) — business rules and financial calculations.
- [PulseHub](https://github.com/duanjesus/pulsehub) — real-time communication (WebSocket/STOMP).
- [PulseQueue](https://github.com/duanjesus/pulsequeue) — event-driven notification infrastructure (RabbitMQ): retry + dead-letter queue, Redis deduplication and rate limiting, observability.

## License

MIT — see [LICENSE](LICENSE).
