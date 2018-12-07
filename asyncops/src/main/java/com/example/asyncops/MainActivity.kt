package com.example.asyncops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disposables.add(
                runnerObs()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            txtDt.text = it
                        }
                        .subscribe()
        )



        btnOk.setOnClickListener {
            doAsync {
                for (i in 1..100) {
                    Thread.sleep(500)
                    runOnUiThread {
                        txtDt.text = i.toString()
                    }
                }
            }
        }
    }

    fun runnerObs(): Observable<String> {
        return Observable.create { sub ->
            for (i in 1..100) {
                Thread.sleep(500)
                sub.onNext("$i")
            }
            sub.onComplete()
        }
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
