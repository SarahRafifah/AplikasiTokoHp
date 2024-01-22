<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ApiController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('login', [ApiController::class, 'login']);
//hp
Route::get('hp/tampil', [ApiController::class, 'gethp']);
Route::post('hp/simpan', [ApiController::class, 'entryhp']);
Route::post('hp/hapus',[ApiController::class, 'hapushp']);
Route::post('hp/edit',[ApiController::class, 'edithp']);

//helper

Route::post('kelurahan/tampil', [ApiController::class, 'kelurahan']);
Route::post('kecamatan/tampil', [ApiController::class, 'kecamatan']);
Route::post('kabkota/tampil', [ApiController::class, 'kabupaten']);
Route::get('provinsi/tampil', [ApiController::class, 'provinsi']);