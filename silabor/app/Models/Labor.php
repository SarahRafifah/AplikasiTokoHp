<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Labor extends Model
{
    protected $table = "labor";
    protected $primaryKey = "id";
    protected $fillable = ['labor','jumlahkomputer'];
}
