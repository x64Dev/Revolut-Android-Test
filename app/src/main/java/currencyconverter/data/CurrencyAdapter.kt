package currencyconverter.data

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import currencyconverter.R
import currencyconverter.domain.Constants
import currencyconverter.domain.ExchangeRate
import currencyconverter.viewmodels.MainVM
import kotlinx.android.synthetic.main.currency_list_item.view.*


class CurrencyAdapter(var list : MutableList<ExchangeRate>, val vm: MainVM) :
    RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    var baseRate : Double = 1.0


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvCurrency = view.tvCurrency
        val etRate = view.etValue
    }

    fun setExchangeList(updatedList : MutableList<ExchangeRate>){
        if(isTextChanging) return

        this.list = updatedList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_list_item, parent, false)

        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val exchangeRate = list[position]

        holder.tvCurrency.text = exchangeRate.mCurrency

        holder.etRate.removeTextChangedListener(tw)


        if(position == 0){
            holder.etRate.setText(String.format("%.2f", baseRate))
        }
        else{
            holder.etRate.setText(String.format("%.2f", exchangeRate.mRate * baseRate))
        }


        if(position == 0) {
            println("Adding Text Change Listener")
            holder.etRate.addTextChangedListener(tw)
        }
        else{
            println("Removing Text Change Listener")
            holder.etRate.removeTextChangedListener(tw)
        }

        holder.itemView.setOnClickListener{

            val temp = list[position]

            Constants.defaultBase = temp.mCurrency

            list.add(position, list[0])
            list.add(0, temp)

            notifyDataSetChanged()
        }
    }

    val handler = Handler();

    var rate : Double = 0.0;

    val runnable = Runnable {

        baseRate = rate

        println("Base rate: " + baseRate)
        isTextChanging = false
        notifyDataSetChanged()

    }

    var isTextChanging = false

    val tw = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {


            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, 500)

          //  vm.updateCurrenciesRate(baseRate, list)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s.toString().isEmpty()) return

            isTextChanging = true

            rate = s.toString().toDouble()

        }
    }


    override fun getItemCount() = list.size
}
