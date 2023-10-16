import 'package:flutter/material.dart';
import 'package:poo_flutter/03-bool-condicion.dart';
import 'package:poo_flutter/05-map.dart';
import 'package:poo_flutter/06-funciones.dart';
import 'package:poo_flutter/07-clase-basica.dart';
import 'package:poo_flutter/08-named-constructor.dart';
import 'package:poo_flutter/09-getters-setters.dart';
import 'package:poo_flutter/10-clases-abstractas.dart';
import 'package:poo_flutter/11-extends.dart';
import 'package:poo_flutter/12-mixins.dart';
import 'package:poo_flutter/13-futures.dart';
import 'package:poo_flutter/14-async-await.dart';

void main() {
  print("Hola Flutter ddd");
  definirVarianles();
  main2();
  main5();
  main6();
  main7();
  main8();
  main9();
  main10();
  main11();
  main12();
  main13();
  main14();
  runApp(const MyApp());
}

void definirVarianles(){
// Strings
  final String nombre = 'Tony';
  final apellido = 'Stark';
   //nombre = 'Peter';
  print('Mostrar valores: $nombre ${apellido}');
// Números
  int empleados = 10;
  double salario = 1856.25;
  print(empleados);
  print(salario);

}


class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const Text('Flutter Demo Home Page dd  sdsds'),
    );
  }
}
