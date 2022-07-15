package com.example.cloudfunctionsmessage.list

import com.example.cloudfunctionsmessage.model.TitleModel

interface OnItemClickListerner {
    fun onItemClick(requestData: TitleModel)
}