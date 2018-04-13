package com.github.flickr.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.github.flickr.App
import com.github.flickr.R
import com.github.flickr.dependency.BaseAppComponent
import com.github.flickr.home.adapter.PhotoFeedAdapter
import com.github.flickr.home.data.PhotoFeedDomain
import javax.inject.Inject


class HomeFragment : Fragment(), HomeFragmentContract.View {

    @BindView(R.id.photos)
    lateinit var photos: RecyclerView
    @BindView(R.id.progress)
    lateinit var progress: ProgressBar

    @Inject
    lateinit var adapter: PhotoFeedAdapter
    @Inject
    lateinit var presenter: HomeFragmentContract.Presenter
    @Inject
    lateinit var itemDecoration: RecyclerView.ItemDecoration

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        ButterKnife.bind(this, view)

        Injector.inject(getBaseComponent(), this).inject(this)

        photos.adapter = adapter
        photos.addItemDecoration(itemDecoration)

        presenter.init()

        return view
    }

    override fun bindData(entries: List<PhotoFeedDomain.EntryDomain>) {
        adapter.bindData(entries)
    }

    override fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        view?.let { view ->
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showError(message: String) {
        view?.let { view ->
            val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackBar.view.background = ContextCompat.getDrawable(context!!, R.color.colorAccent)
            snackBar.show()
        }
    }

    override fun onDestroyView() {
        presenter.clearSubscriptions()
        super.onDestroyView()
    }

    private fun getBaseComponent(): BaseAppComponent {
        return (activity?.application as App).baseComponent
    }

    companion object Injector {
        fun inject(baseComponent: BaseAppComponent,
                   view: HomeFragmentContract.View): HomeFragmentComponent {
            return DaggerHomeFragmentComponent
                    .builder()
                    .baseAppComponent(baseComponent)
                    .homeFragmentModule(HomeFragmentModule(view))
                    .build()

        }

    }
}
