package com.craftinginterpreters.britlang;

import java.util.List;

interface BritlangCallable
{
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
