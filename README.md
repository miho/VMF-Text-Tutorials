# VMF-Text-Tutorials  [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=VMF-Text:%20The%20new%20framework%20for%20grammar-based%20language%20modeling!&url=https://github.com/miho/VMF-Text&via=mihosoft&hashtags=vmftext,vmf,antlr4,java,mdd,developers)

[![Join the chat at https://gitter.im/VMF_/Lobby](https://badges.gitter.im/VMF_/Lobby.svg)](https://gitter.im/VMF_/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This collection of tutorials gives an introduction to VMF and its use in regular Java projects.

## Contents

- [Introduction](https://github.com/miho/VMF-Text-Tutorials/blob/master/README.md#introduction)
- [Defining your first Language](https://github.com/miho/VMF-Text-Tutorials/blob/master/VMF-Text-Tutorial-01/README.md)
- TBD ...



## Introduction

VMF-Text is a powerful framework for grammar-based language modeling: give it a labeled ANTLR4 grammar and it will generate a rich and clean API (based on [VMF](https://github.com/miho/VMF)) for (un)parsing and transforming custom textual languages. **The complete API is derived from just a single ANTLR4 grammar file!**

<img src="https://github.com/miho/VMF-Text/blob/master/resources/img/vmf-text-01.jpg">

VMF-Text analyzes an ANTLR4 grammar file and derives a [VMF](https://github.com/miho/VMF) model from that. With that it generates the model code, just like any regular VMF based application. Additionally, it creates a very powerful API for parsing and (un)parsing instances of the VMF model. This allows for writing tools for analyzing and transforming textual data, such as code.

