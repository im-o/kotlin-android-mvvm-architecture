package com.rivaldy.id.pharmacist.data

import com.rivaldy.id.pharmacist.data.local.db.DbHelper
import com.rivaldy.id.pharmacist.data.local.pref.PreferencesHelper
import com.rivaldy.id.pharmacist.data.remote.ApiHelper

/**
 * Created by rivaldy on 10/07/21
 * Find me on my Github -> https://github.com/im-o
 **/

interface DataManager : DbHelper, PreferencesHelper, ApiHelper