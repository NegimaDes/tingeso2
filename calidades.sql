PGDMP     3    ,                {         	   calidades    12.14    12.14                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    25999 	   calidades    DATABASE     �   CREATE DATABASE calidades WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE calidades;
                postgres    false            �            1259    26002    calidad    TABLE     �   CREATE TABLE public.calidad (
    id integer NOT NULL,
    codigo integer,
    grasas integer,
    solidos integer,
    anno integer,
    mes integer,
    quincena integer
);
    DROP TABLE public.calidad;
       public         heap    postgres    false            �            1259    26000    calidad_id_seq    SEQUENCE     �   ALTER TABLE public.calidad ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.calidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �
           2606    26006    calidad calidad_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.calidad
    ADD CONSTRAINT calidad_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.calidad DROP CONSTRAINT calidad_pkey;
       public            postgres    false    203           