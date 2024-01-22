<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Pupuk extends Model
{
    protected $table = "pupuk";
    protected $primaryKey = "id";
    protected $fillable = ['pupuk',  'jumlah'];  
}
