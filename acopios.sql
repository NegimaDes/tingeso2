PGDMP         -                {            acopios    12.14    12.14                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    25990    acopios    DATABASE     �   CREATE DATABASE acopios WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE acopios;
                postgres    false            �            1259    25993    acopio    TABLE     �   CREATE TABLE public.acopio (
    id integer NOT NULL,
    codigo integer NOT NULL,
    totalkls integer,
    manana integer,
    tarde integer,
    entregas integer,
    anno integer,
    mes integer,
    quincena integer
);
    DROP TABLE public.acopio;
       public         heap    postgres    false            �            1259    25991    acopio_id_seq    SEQUENCE     �   ALTER TABLE public.acopio ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.acopio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �
           2606    25997    acopio acopio_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.acopio
    ADD CONSTRAINT acopio_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.acopio DROP CONSTRAINT acopio_pkey;
       public            postgres    false    203           