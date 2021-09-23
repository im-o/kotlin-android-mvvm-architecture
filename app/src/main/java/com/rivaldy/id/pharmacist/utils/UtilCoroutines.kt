package com.rivaldy.id.pharmacist.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by rivaldy on 01/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

object UtilCoroutines {
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch { work() }
    fun io(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch { work() }
}