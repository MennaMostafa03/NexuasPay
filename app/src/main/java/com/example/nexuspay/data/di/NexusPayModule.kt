package com.example.nexuspay.data.di


import androidx.room.Room
import androidx.work.WorkerFactory
import com.example.nexuspay.data.local_ds.card.CardLocalData
import com.example.nexuspay.data.local_ds.card.CardLocalDataImpl
import com.example.nexuspay.data.setup.api.createHttpClient
import com.example.nexuspay.data.setup.connectivity.Connectivity
import com.example.nexuspay.data.setup.connectivity.ConnectivityImpl
import com.example.nexuspay.data.setup.database.TransactionDataBase
import com.example.nexuspay.data.repository.UserRepoImpl
import com.example.nexuspay.data.local_ds.transaction.TransactionLocalData
import com.example.nexuspay.data.local_ds.transaction.TransactionLocalDataImpl
import com.example.nexuspay.data.local_ds.user.UserLocalData
import com.example.nexuspay.data.local_ds.user.UserLocalDataImpl
import com.example.nexuspay.data.remote_ds.transaction.TransactionRemoteData
import com.example.nexuspay.data.remote_ds.transaction.TransactionRemoteDataImpl
import com.example.nexuspay.data.remote_ds.user.UserRemoteData
import com.example.nexuspay.data.remote_ds.user.UserRemoteDataImpl
import com.example.nexuspay.data.repository.CardRepoImpl
import com.example.nexuspay.data.repository.TransactionRepoImpl
import com.example.nexuspay.data.setup.database.CardDataBase
import com.example.nexuspay.data.setup.database.RequestDataBase
import com.example.nexuspay.domain.repository.CardRepo
import com.example.nexuspay.domain.repository.TransactionRepo
import com.example.nexuspay.domain.repository.UserRepo
import com.example.nexuspay.domain.usecase.card.AddCardUseCase
import com.example.nexuspay.domain.usecase.card.GetCardUseCase
import com.example.nexuspay.domain.usecase.transaction.SaveRequestUseCase
import com.example.nexuspay.domain.usecase.transaction.GetTransactionUseCase
import com.example.nexuspay.domain.usecase.user.GetUserUseCase
import com.example.nexuspay.domain.usecase.transaction.GetAllUserUseCase
import com.example.nexuspay.domain.usecase.transaction.RetrySendMoneyUseCase
import com.example.nexuspay.ui.screens.bottom_nav.card.viewmodel.CardViewModel
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.CommonViewModel
import com.example.nexuspay.ui.screens.bottom_nav.home.viewmodel.SendViewModel
import com.example.nexuspay.workmanager.ScheduleManager
import com.example.nexuspay.workmanager.ScheduleManagerImpl
import com.example.nexuspay.workmanager.TransactionWorker
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.androidx.workmanager.dsl.worker
import org.koin.androidx.workmanager.factory.KoinWorkerFactory

val nexusPayModule = module {

    // online
    single<HttpClient>{ createHttpClient() }
    single<UserRemoteData>{ UserRemoteDataImpl(get()) }
    single<TransactionRemoteData>{ TransactionRemoteDataImpl(get()) }


    // offline
    single{
        Room.databaseBuilder(
            androidContext(),
            TransactionDataBase::class.java,
            "transaction_database"
        ).build()
    }

    single{
        Room.databaseBuilder(
            androidContext(),
            RequestDataBase::class.java,
            "request_database"
        ).build()
    }

    single{
        Room.databaseBuilder(
            androidContext(),
            CardDataBase::class.java,
            "card_database"
        ).build()
    }

    single { get<RequestDataBase>().getRequestDao()}

    single { get<TransactionDataBase>().getTransactionDao()}

    single { get<CardDataBase>().getCardDao()}

    single<UserLocalData>{ UserLocalDataImpl(get()) }
    single<TransactionLocalData>{ TransactionLocalDataImpl(get(), get()) }
    single<CardLocalData>{ CardLocalDataImpl(get()) }


    // connectivity
    single<Connectivity> {
        ConnectivityImpl(androidContext())
    }


    // repository
    single<UserRepo>{ UserRepoImpl(
        get(),
        get(),
        get()) }
    single<TransactionRepo>{ TransactionRepoImpl(
            get(),
            get(),
            get(),
            get()) }

    single<CardRepo>{ CardRepoImpl(get())}


    // usecase
    single{ GetUserUseCase(get()) }
    single{ GetTransactionUseCase(get()) }
    single{ GetAllUserUseCase(get()) }
    single{ SaveRequestUseCase(get()) }
    single{ RetrySendMoneyUseCase(get()) }
    single{ AddCardUseCase(get()) }
    single{ GetCardUseCase(get()) }


    // viewmodel
    single{ CommonViewModel(get(), get()) }
    viewModel{ SendViewModel(get(), get()) }
    viewModel{ CardViewModel(get(),get()) }


    // worker
    worker { TransactionWorker(get(), get(), get())}
    single <ScheduleManager>{ ScheduleManagerImpl(get()) }
    single<WorkerFactory> { KoinWorkerFactory() }

}