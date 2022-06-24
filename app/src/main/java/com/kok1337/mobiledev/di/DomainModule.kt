package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.data.repository.*
import com.kok1337.mobiledev.domain.repository.*
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

    @Binds @Suppress("FunctionName")
    fun bindSubForestryRepoImpl_to_SubForestryRepo(subForestryRepoImpl: SubForestryRepoImpl): SubForestryRepo

    @Binds @Suppress("FunctionName")
    fun bindAreaRepoImpl_to_AreaRepo(areaRepoImpl: AreaRepoImpl): AreaRepo

}




