package com.mycomp.githubrepositories.core

interface Mapper<TargetModel, ConvertedModel> {
    fun map(targetModel: TargetModel):ConvertedModel
}