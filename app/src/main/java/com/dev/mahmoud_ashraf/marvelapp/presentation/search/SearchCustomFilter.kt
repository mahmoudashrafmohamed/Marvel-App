package com.dev.mahmoud_ashraf.marvelapp.presentation.search

import android.widget.Filter
import com.dev.mahmoud_ashraf.marvelapp.data.model.CharactersResponse

import java.util.ArrayList


//This is the class that will search our data.
class SearchCustomFilter(internal var filterList: MutableList<CharactersResponse.Result>, internal var adapter: SearchAdapter) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val results = FilterResults()

        if (constraint != null && constraint.length > 0) {
            constraint = constraint.toString().toUpperCase()

            val filteredCountries = ArrayList<CharactersResponse.Result>()
            for (i in filterList.indices) {
                if (filterList[i]?.name?.toUpperCase()?.contains(constraint)!!) {
                    filteredCountries.add(filterList[i])
                }
            }

            results.count = filteredCountries.size
            results.values = filteredCountries
        } else {
            results.count = filterList.size
            results.values = filterList

        }


        return results
    }

    override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
        adapter.items = filterResults.values as ArrayList<CharactersResponse.Result>
        adapter.notifyDataSetChanged()
    }
}
