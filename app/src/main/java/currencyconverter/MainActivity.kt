package currencyconverter

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import currencyconverter.utils.Injector
import currencyconverter.data.CurrencyAdapter
import currencyconverter.domain.Constants
import currencyconverter.domain.ExchangeRate

import kotlinx.android.synthetic.main.activity_main.*
import currencyconverter.viewmodels.MainVM
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: MainVMFactory

    lateinit var vm : MainVM

    var list: MutableList<ExchangeRate> = mutableListOf()

    lateinit var adapter : CurrencyAdapter

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupVM()

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CurrencyAdapter(list, vm)

        recyclerView.adapter = adapter
    }


    private fun setupVM(){
        Injector.get().inject(this)

        vm = ViewModelProviders.of(this, vmFactory).get(MainVM::class.java)
        vm.currenciesLiveData.observe(this, Observer {
            if(it != null){
              adapter.setExchangeList(it)
            }
        })
        vm.progressBarVisibilityLiveData.observe(this, Observer {
            progressBar.visibility = if(it) View.VISIBLE else View.GONE
        })

        handler.post(runnable)
    }

    val runnable = object : Runnable {
        override fun run() {
            try{
                vm.getCurrencies(Constants.defaultBase)
            }
            finally {
                handler.postDelayed(this, 1000)
            }
        }
    }
}
