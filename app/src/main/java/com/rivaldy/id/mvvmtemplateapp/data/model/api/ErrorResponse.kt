package com.rivaldy.id.mvvmtemplateapp.data.model.api


import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    /**
     * TODO-CHANGE
     * CHANGE THIS DATA CLASS WITH YOUR ERROR RESPONSE API
     **/

    @SerializedName("status_code")
    var statusCode: Int? = null,
    @SerializedName("status_message")
    var statusMessage: String? = null,
    @SerializedName("success")
    var success: Boolean? = null
)