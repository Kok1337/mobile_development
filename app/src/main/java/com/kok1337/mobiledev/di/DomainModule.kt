package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.data.repository.FederalDistrictRepoImpl
import com.kok1337.mobiledev.data.repository.ForestryRepoImpl
import com.kok1337.mobiledev.data.repository.SubjectOfRusFedRepoImpl
import com.kok1337.mobiledev.data.repository.WorkTypeRepoImpl
import com.kok1337.mobiledev.domain.repository.FederalDistrictRepo
import com.kok1337.mobiledev.domain.repository.ForestryRepo
import com.kok1337.mobiledev.domain.repository.SubjectOfRusFedRepo
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo
import dagger.Binds
import dagger.Module

@Module(includes = [DomainBindModule::class])
class DomainModule

@Module
interface DomainBindModule {

    @Binds @Suppress("FunctionName")
    fun bindFederalDistrictRepoImpl_to_FederalDistrictRepo(federalDistrictRepoImpl: FederalDistrictRepoImpl): FederalDistrictRepo

    @Binds @Suppress("FunctionName")
    fun bindWorkTypeRepoImpl_to_WorkTypeRepo(workTypeRepoImpl: WorkTypeRepoImpl): WorkTypeRepo

    @Binds @Suppress("FunctionName")
    fun bindSubjectOfRusFedRepoImpl_to_SubjectOfRusFedRepo(subjectOfRusFedRepoImpl: SubjectOfRusFedRepoImpl): SubjectOfRusFedRepo

    @Binds @Suppress("FunctionName")
    fun bindForestryRepoImpl_to_ForestryRepo(forestryRepoImpl: ForestryRepoImpl): ForestryRepo

}




