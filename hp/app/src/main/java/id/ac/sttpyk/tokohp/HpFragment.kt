import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.sttpyk.tokohp.R
import id.ac.sttpyk.tokohp.api.Api
import id.ac.sttpyk.tokohp.databinding.FragmentHpBinding
import id.ac.sttpyk.tokohp.helper.AdapterHp
import id.ac.sttpyk.tokohp.models.HpModel
import id.ac.sttpyk.tokohp.models.SimpanModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HpFragment : Fragment() {

    private lateinit var binding : FragmentHpBinding
    private val api by lazy{ Api.create()}
    private val navController  by lazy { findNavController() }

    companion object{
        const val HP = "HP"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        api.tampilhp().enqueue(object : Callback<HpModel>{
            override fun onResponse(call: Call<HpModel>, response: Response<HpModel>) {
                if (response.isSuccessful){
                    Log.e("TAG", "onResponse: ${response.body()}",)
                    val result = response.body()!!.data
                    binding.listdatahp.layoutManager =
                        LinearLayoutManager(requireContext())
                    binding.listdatahp.itemAnimator = null
                    val adapterHp = AdapterHp(result as ArrayList<HpModel.Data>,
                        object : AdapterHp.OnAdapterListener {
                            override fun OnClick(hp: HpModel.Data) {
                                var bundle = Bundle().apply {
                                    putParcelable(HP, hp)
                                }
                                navController.navigate(R.id.action_hpFragment_to_tambahDataFragment,bundle)
                            }
                        })
                    adapterHp.setOnItemClickCallback {
                        api.hapushp(it.id).enqueue(object : Callback<SimpanModel>{
                            override fun onResponse(call: Call<SimpanModel>, response: Response<SimpanModel>
                            ) {
                                val submit = response.body()
                                if (response.isSuccessful){
                                    Toast.makeText(requireContext(), submit!!.message, Toast.LENGTH_SHORT).show()
                                    val deleteindex = result.indexOf(it)
                                    result.removeAt(deleteindex)
                                    adapterHp.notifyItemChanged(deleteindex)
                                }
                            }

                            override fun onFailure(call: Call<SimpanModel>, t: Throwable) {
                                Toast.makeText(requireContext(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    binding.listdatahp.adapter = adapterHp
                }
            }

            override fun onFailure(call: Call<HpModel>, t: Throwable) {
                Toast.makeText(requireContext(),"Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.kembali.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.tambah.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_hpFragment_to_tambahDataFragment)
        )
    }

}