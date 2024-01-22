<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Alat extends Model
{
    protected $table = "alat";
    protected $primaryKey = "id";
    protected $fillable = ['alat',  'jumlah'];  
}
