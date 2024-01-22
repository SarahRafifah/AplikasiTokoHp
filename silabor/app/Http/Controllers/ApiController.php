<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;
use App\Models\User;
use App\Models\Labor;
use App\Models\Hp;
use App\Models\Kelurahan;
use App\Models\Kecamatan;
use App\Models\Kabupaten;
use App\Models\Provinsi;

class ApiController extends Controller
{
    public function login(Request $request)
    {
        $login = Auth::Attempt($request->all());
        if ($login) {
            $user = Auth::user();
            // $user->api_token = Str::random(100);
            // $user->save();
            // $user->makeVisible('api_token');

            return response()->json([
                'response_code' => 200,
                'message' => 'Login Berhasil',
                'conntent' => $user
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Username atau Password Tidak Ditemukan!'
            ]);
        }
    }
    //hp
    public function gethp()
    {
        $hp = Hp::select('*')
            ->get();

     if ($hp) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $hp
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        } 
    }  

    public function entryhp(Request $request)

    {

        $hp = Hp::create([
            'hp' => $request->hp,
            'harga' => $request->harga,
        ]);




        if ($hp) { // jika simpan santri berhasil

            return response()->json([
                'response_code' => 200,
                'message' => 'Simpan Data Berhasil',
            ]);

        }else{ // jika simpan santri gagal

            return response()->json([
                'response_code' => 404,
                'message' => 'Simpan Data Gagal'
            ]);

        }

    }

    public function hapushp(Request $request)
    {
        $hp = Hp::where('id', $request->id)
                  ->delete();

        if ($hp) {
            return response()->json([
                'response_code' => 200,
                'message' => 'Hapus Data Berhasil'
            ]);
        }else{
            return response()->json([
                'response_code' => 404,
                'message' => 'Hapus Data Gagal'
            ]);
        }
    }

    function edithp(Request $request) {
        $hp = Hp::where('id', $request->id)
                  ->update([
                    'hp' => $request->hp,
                    'harga' => $request->harga,
                  ]);

        if ($hp) {
            return response()->json([
                'response_code' => 200,
                'message' => 'ok',
            ]);
        } else {
            return response()->json([
                'response_code' => 500,
                'message' => 'internal error',
            ]);
        }
    }

  
//helper
    public function kelurahan(Request $request)
    {
        $kelurahan = Kelurahan::select('*')
            ->where('id_kec', $request->id_kec)
            ->where('in_active', 0)
            ->get();

            if ($kelurahan) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kelurahan
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }   
    public function kecamatan(Request $request)
    {
        $kecamatan = Kecamatan::select('*')
            ->where('id_kab', '=', $request->id_kab)
            ->get();

            if ($kecamatan) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kecamatan
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }
    public function kabupaten(Request $request)
    {
        $kabupaten = Kabupaten::select('*')
            ->where('id_prov', '=', $request->id_prov)
            ->get();

            if ($kabupaten) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $kabupaten
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }    




      
    public function provinsi(Request $request)
    {
        $provinsi = Provinsi::select('*')
            ->get();

            if ($provinsi) { 
            return response()->json([
                'response_code' => 200,
                'message' => 'Tarik Data Berhasil',
                'data' => $provinsi
            ]);
        } else { 
            return response()->json([
                'response_code' => 404,
                'message' => 'Tarik Data Gagal'
            ]);
        }
    }   
}
