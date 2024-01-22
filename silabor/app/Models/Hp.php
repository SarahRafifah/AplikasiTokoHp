<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Hp extends Model
{
    protected $table = "hp";
    protected $primaryKey = "id";
    protected $fillable = ['hp',  'harga'];  
}
