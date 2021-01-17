<h1 align="center" id="home">
  <a href="https://github.com/cuongw/ting-coffee">
    <img alt="ting-coffee" src="https://user-images.githubusercontent.com/34389409/51270422-6db97d00-19f7-11e9-993e-883a92b0c2d5.png" width="300">
  </a>
  <br>Ting Coffee<br>
</h1>

<h4 align="center">
  Managemet App for <a href="https://github.com/uiters/ting-coffee" target="_blank"><code>Ting Coffee</code></a>.
</h4>

<p align="center">
  <a href="https://travis-ci.org/uiters/ting-coffee">
    <img src="https://travis-ci.org/uiters/ting-coffee.svg?branch=master"/>
  </a>
  
  <a href="https://github.com/uiters/ting-coffee/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/cuongw/ting-coffee.svg"/>
  </a>
</p>

<div align="center">
  <h4>
    <a href="#features">Features</a> |
    <a href="#structure">Structure</a> |
    <a href="#install">Install</a> |
    <a href="#usage">Usage</a> |
    <a href="#documents">Documents</a> |
    <a href="#technologies">Technologies</a> |
    <a href="#some-screens">Some Screens</a> |
    <a href="#bugs-and-issues">Bugs and Issues</a> |
    <a href="#team">Team</a> |
    <a href="#license">License</a>
  </h4>
</div>

## Features

* Ordering drinks, foods by table.
* Checkout, preview & print invoice.
* Profile feature.
* Manage drink, food & category.
* Manage table.
* Manage account.
* Sales report.

## Structure

<p align="center">
<img src="https://user-images.githubusercontent.com/34389409/51085703-b6f79b80-176f-11e9-8684-378db7d5844d.png" width="500"/>
</p>

## Install

Clone this project:
```sh
~$ git clone https://github.com/uiters/ting-coffee.git
```
cd `ting-coffee`:
```sh
~$ cd ting-coffee
```
Install packages for `flutter` apps:
```bash
~$ flutter packages get
```
Setup API:

* Upload file: ```index.php``` at ```ting-coffee/server/Normal``` to your host.
* Edit ```index.php```
  ```
  $servername = "Your servername";
  $username = "your username";
  $password = "your password";
  $dbname = "database name";
  ```
* Edit line 1 ```evn.dart``` at ```store-pattern/order_app/lib/Constants/```

    ```dart
    const String URL_EXECUTE = "your domain/index.php";
    ```

* Edit line 17 ```kitchen_app/src/Constants/Constant.java``` and ```admin_app/src/Constants/Constant.java```


    ```java
    public static String urlConnect = "your domain/index.php";
    ```

* Run script sql in your PhpMyAdmin ```ting-coffee/database/mysql.sql```

Run:

* Requrie install ```ant```

	```bash
	~$ sudo apt install ant
	```

* Run admin_app:

	```bash
	~$ cd admin_app
	~$ ant run
	```

* Run kitchen_app

	``` bash
	~$ cd kitchen_app
	~$ ant run
	```

* Run order_app

⚠ Require: Flutter version `1.26.0-1.0.pre`

	```bash
	~$ cd order_app
	~$ flutter run
	```
## Usage

Using this account for testing:</br>
**`username`**: `test`</br>
**`password`**: `test`</br>

Enjoy 👍

## Documents

Using [Store Pattern](https://github.com/cuongw/store-pattern).

## Technologies

* [Flutter](https://flutter.io/)
* [PHP](http://php.net/)
* [MySQL](https://www.mysql.com/)
* [Java](https://www.java.com/en/download/)

## Some Screens

### `Order App`

<div style="text-align: center"><table><tr>
  <td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
    <img src="https://user-images.githubusercontent.com/34389409/53300996-2937c180-3880-11e9-8f4d-f30c16f3a609.png" width="200"/></a>
</td>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075787-02a03b80-16c3-11e9-89b0-7a50f305e0fb.png" width="200"/>
  </a>
</td>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075788-02a03b80-16c3-11e9-81ab-46a192d38e58.png" width="200" />
  </a>
</td>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075790-02a03b80-16c3-11e9-8a8e-3b539dde99f5.png" width="200"/>
  </a>
</td>
</tr></table></div>

<div style="text-align: center"><table><tr>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075791-0338d200-16c3-11e9-9395-f035e4a6a24f.png" width="200"/>
  </a>
</td>
<td style="text-align: center">
<a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
  <img src="https://user-images.githubusercontent.com/34389409/51075792-0338d200-16c3-11e9-93a2-ca4837bf2f26.png" width="200"/></a>
</td>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075793-03d16880-16c3-11e9-8a6f-f277cfd455bc.png" width="200" />
  </a>
</td>
<td style="text-align: center">
  <a href="https://github.com/cuongw/ting-coffee/tree/master/order_app">
<img src="https://user-images.githubusercontent.com/34389409/51075874-0b454180-16c4-11e9-962c-f597b0f6e15a.png" width="200"/>
  </a>
</td>

</tr></table></div>

### `Admin App`
<a href="https://github.com/cuongw/ting-coffee/tree/master/admin_app">
<img src="https://user-images.githubusercontent.com/34389409/51789297-04392b80-21ba-11e9-91fb-1c23721c2776.png" width="900"/>
</a>
<a href="https://github.com/cuongw/ting-coffee/tree/master/admin_app">
<img src="https://user-images.githubusercontent.com/34389409/51789304-12874780-21ba-11e9-85ac-d1f62304c367.png" width="900"/>
</a>
<a href="https://github.com/cuongw/ting-coffee/tree/master/admin_app">
<img src="https://user-images.githubusercontent.com/34389409/51789308-1fa43680-21ba-11e9-9eee-68e58c6fb2fc.png" width="900"/>
</a>
<a href="https://github.com/cuongw/ting-coffee/tree/master/admin_app">
<img src="https://user-images.githubusercontent.com/34389409/51789310-2f237f80-21ba-11e9-9a8c-5a227a0ccaf1.png" width="900"/>
</a>
  
### `Kitchen App`

<a href="https://github.com/cuongw/ting-coffee/tree/master/kitchen_app">
<img src="https://user-images.githubusercontent.com/34389409/51789316-406c8c00-21ba-11e9-8470-fd1763b81826.png" width="900"/>
</a>

## Bugs and Issues

Have a bug or an issue with this project? [Open a new issue](https://github.com/cuongw/ting-coffee/issues) here on GitHub.

## Team

[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/0)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/0)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/1)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/1)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/2)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/2)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/3)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/3)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/4)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/4)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/5)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/5)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/6)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/6)[![](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/images/7)](https://sourcerer.io/fame/cuongw/tvc12/ting-coffee/links/7)

**[⬆ back to top](#home)**

## License

[MIT](https://github.com/uiters/ting-coffee/blob/master/LICENSE)
