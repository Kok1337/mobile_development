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

    @Binds @Suppress("FunctionName")
    fun bindSectionRepoImpl_to_SectionRepo(sectionRepoImpl: SectionRepoImpl): SectionRepo

    @Binds @Suppress("FunctionName")
    fun bindTaxSourceRepoImpl_to_TaxSourceRepo(taxSourceRepoImpl: TaxSourceRepoImpl): TaxSourceRepo

    @Binds @Suppress("FunctionName")
    fun bindTaxYearRepoImpl_to_TaxYearRepo(taxYearRepoImpl: TaxYearRepoImpl): TaxYearRepo

    @Binds @Suppress("FunctionName")
    fun bindCacheRepoImpl_to_CacheRepo(cacheRepoImpl: CacheRepoImpl): CacheRepo

    @Binds @Suppress("FunctionName")
    fun bindInfoTaxRepoImpl_to_InfoTaxRepo(infoTaxRepoImpl: InfoTaxRepoImpl): InfoTaxRepo

    @Binds @Suppress("FunctionName")
    fun bindPlantingCharacteristicRepoImpl_to_PlantingCharacteristicRepo(plantingCharacteristicRepoImpl: PlantingCharacteristicRepoImpl): PlantingCharacteristicRepo

    @Binds @Suppress("FunctionName")
    fun bindPLandCategoryRepoImpl_to_LandCategoryRepo(landCategoryRepoImpl: LandCategoryRepoImpl): LandCategoryRepo

    @Binds @Suppress("FunctionName")
    fun bindProtectionCategoryRepoImpl_to_ProtectionCategoryRepo(protectionCategoryRepoImpl: ProtectionCategoryRepoImpl): ProtectionCategoryRepo

    @Binds @Suppress("FunctionName")
    fun bindTargetCategoryRepoImpl_to_TargetCategoryRepo(targetCategoryRepoImpl: TargetCategoryRepoImpl): TargetCategoryRepo

    @Binds @Suppress("FunctionName")
    fun bindOoptRepoImpl_to_OoptRepo(ooptRepoImpl: OoptRepoImpl): OoptRepo

    @Binds @Suppress("FunctionName")
    fun bindOzuRepoImpl_to_OzuRepo(ozuRepoImpl: OzuRepoImpl): OzuRepo

    @Binds @Suppress("FunctionName")
    fun bindBonitetRepoImpl_to_BonitetRepo(bonitetRepoImpl: BonitetRepoImpl): BonitetRepo

    @Binds @Suppress("FunctionName")
    fun bindTluRepoImpl_to_TluRepo(tluRepoImpl: TluRepoImpl): TluRepo

    @Binds @Suppress("FunctionName")
    fun bindOriginRepoImpl_to_OriginRepo(originRepoImpl: OriginRepoImpl): OriginRepo

    @Binds @Suppress("FunctionName")
    fun bindLandRepoImpl_to_LandRepo(landRepoImpl: LandRepoImpl): LandRepo
}




