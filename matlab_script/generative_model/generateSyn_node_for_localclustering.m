clear;
clc;

dim=4;
for i=1:1
    att=100;
    ratio=0.5;
    for j=[20]
        %     for j=1000
        j
        filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\' num2str(i) '\' num2str(j) '\label.mat'];
        load(filename);
        
        n=size(label,1);
        numClus=4;
        index1=zeros(j/numClus,1);
        index2=zeros(j/numClus,1);
        index3=zeros(j/numClus,1);
        index4=zeros(j/numClus,1);
        
        clusterSize=length(index1);
        r=randperm(clusterSize);
        point=floor(clusterSize/2);
        index1(r(1:point),1)=1;
        index1(r(point+1:end),1)=2;
        
        r=randperm(clusterSize);
        index2(r(1:point),1)=3;
        index2(r(point+1:end),1)=4;
        
        r=randperm(clusterSize);
        index3(r(1:point),1)=5;
        index3(r(point+1:end),1)=6;
        
        r=randperm(clusterSize);
        index4(r(1:point),1)=7;
        index4(r(point+1:end),1)=8;
        
        
        newlabel=[index1;index2;index3;index4];
        filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\' num2str(i) '\' num2str(j) '\newlabel.mat'];
        save(filename,'newlabel');
        
        releDim=ratio*dim;
        irreleDim=dim-releDim;
        data1=zeros(n,releDim);
        
        
        for k=1:numClus*2
            index=find(newlabel(:,1)==k);
            num=length(index);
            
            re=0.001*randn(num,releDim);
            re=addMean(re);
            
            for i1=1:num
                data1(index(i1),:)=re(i1,:);
            end
        end
        
        data2=zeros(n,irreleDim);
        for j1=1:irreleDim
            pp=randperm(n);
            label1=newlabel(pp,:);
            for k=1:numClus*2
                index=find(label1(:,1)==k);
                num=length(index);
                
                ir=randn(num,1);
                ir=addMean2(ir);
                data2(index,j1)=ir;
            end
        end
        data=[data1,data2];
        %         for k=1:numClus
        %             index=find(label(:,2)==k);
        %             pp=randperm(dim);
        %             data(index,:)=data(index,pp);
        %         end
        
        filename=['C:\Users\Desktop\local clustering\synthetic graph data\intro\' num2str(i) '\' num2str(j) '\data.txt'];
        fileID = fopen(filename,'w');
        [row,col]=size(data);
        for ii=1:row
            for jj=1:col
                fprintf(fileID, '%f ', data(ii,jj));
            end
            fprintf(fileID, '\n');
        end
        fclose(fileID);
        data=[];
    end
end
